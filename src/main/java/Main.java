import freemarker.template.Configuration;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

import freemarker.template.Template;
import modelo.*;
import org.jasypt.util.text.BasicTextEncryptor;
import serivicios.*;
import spark.Session;
import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {


        //staticFiles.externalLocation("src/main/resources/Template/upload/temp");
        staticFiles.externalLocation("src/main/resources/Template");


        UsuarioService usuarioService = new UsuarioService();
        PostService postService = new PostService();
        ComentarioService comentarioService = new ComentarioService();
        MegustaService megustaService = new MegustaService();
        AlbumService albumService = new AlbumService();
        //staticFiles.location("/Template");
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassForTemplateLoading(Main.class, "/");


        get("/", (req, res) -> {
            Usuario usuario = req.session(true).attribute("usuario");
            if (usuario == null && req.cookie("user") != null) {
                BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
                textEncryptor.setPasswordCharArray("rajadura27".toCharArray());
                req.session(true);
                req.session().attribute("usuario", usuarioService.checkUser(textEncryptor.decrypt(req.cookie("user"))));
            }
            res.redirect("/login");
            return "";
        });
        get("/login", (req, res) -> {
            Usuario usuario = req.session(true).attribute("usuario");
            if (usuario != null)
                res.redirect("/home");
            StringWriter writer = new StringWriter();
            Map<String, Object> atr = new HashMap<>();
            Template template = configuration.getTemplate("Template/login.ftl");
            template.process(atr, writer);
            return writer;
        });

        get("/signup", (req, res) -> {

            StringWriter writer = new StringWriter();
            Map<String, Object> atr = new HashMap<>();
            Template template = configuration.getTemplate("Template/signup.ftl");
            if (req.queryParams("error") != null)
                atr.put("error", Integer.parseInt(req.queryParams("error")));
            template.process(atr, writer);
            return writer;
        });

        get("/sugerencias", (req, res) -> {
            Usuario usuario = req.session(true).attribute("usuario");
            StringWriter writer = new StringWriter();
            Map<String, Object> atr = new HashMap<>();
            Template template = configuration.getTemplate("Template/sugerencias.ftl");
            atr.put("usuario",usuario);
            atr.put("sugerAmigo",usuarioService.sugerirAmigos(usuario));
            template.process(atr, writer);
            return writer;
        });
        get("/friendlist", (req, res) -> {
            Usuario usuario = req.session(true).attribute("usuario");
            StringWriter writer = new StringWriter();
            Map<String, Object> atr = new HashMap<>();
            Template template = configuration.getTemplate("Template/friendlist.ftl");
            atr.put("usuario",usuario);
            atr.put("friendList",usuario.getFriendlist());
            template.process(atr, writer);
            return writer;
        });

        get("/timeline", (req, res) -> {
            Usuario usuario = req.session(true).attribute("usuario");
            StringWriter writer = new StringWriter();
            Map<String, Object> atr = new HashMap<>();
            Template template = configuration.getTemplate("Template/timeline.ftl");


            if(usuario.getFriendlist() != null){
                for(int i = 0; i < usuario.getFriendlist().size(); i++){
                    smartCombine(usuario.getWall(),usuario.getFriendlist().get(i).getWall());
                }
            }
            List<Post> wall = usuario.getWall();
            List<Comentario> comentarios = comentarioService.getComentarios();
            List<Megusta> megusta = megustaService.getMegusta();
            atr.put("usuario",usuario);
            atr.put("wall",wall);
            atr.put("comentarios",comentarios);
            atr.put("megusta",megusta);
            template.process(atr,writer);
            return writer;
        });

        get("/albums", (req, res) -> {
            Usuario usuario = req.session(true).attribute("usuario");
            StringWriter writer = new StringWriter();
            Map<String, Object> atr = new HashMap<>();
            Template template = configuration.getTemplate("Template/album.ftl");
            atr.put("usuario",usuario);
            atr.put("aPost",albumService.getAlbumsPosts());
            template.process(atr, writer);
            return writer;
        });
        post("/newAlbum", (req, res) -> {

            Usuario usuario = req.session(true).attribute("usuario");
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("src/main/resources/Template/upload/temp"));
            Collection<Part> parts = null;

            String descripcion = req.queryParams("descripcion");
            Album album = new Album();
            album.setUsuario(usuario);
            album.setDescripcion(descripcion);
            albumService.guardarAlbum(album);
            try {
                parts = req.raw().getParts();
            } catch (IOException | ServletException e2) {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }
            for (Part part : parts) {
                if(part.getName().equals("myfile")){
                    try (InputStream inputStream = part.getInputStream()) {
                        OutputStream outputStream = new FileOutputStream("src/main/resources/Template/upload/temp/" + part.getSubmittedFileName());
                        IOUtils.copy(inputStream, outputStream);
                        outputStream.close();

                        Post post = new Post();
                        post.setDescripcion("n/a");
                        post.setUsuario(usuario);
                        System.out.println(part.getSubmittedFileName());
                        post.setImg(part.getSubmittedFileName());
                        post.setAlbum(album);
                        postService.savePost(post);


                    }
                }


            }
            usuarioService.newAlbum(usuario,album);
            res.redirect("/albums");
            return"";

        });

        post("/addFriend/:idAm", (req, res) -> {

            Usuario usuario = req.session(true).attribute("usuario");
            String texto = req.params("idAm").replace(",", "");
            Usuario amigo = usuarioService.getUsuarioId(Long.parseLong(texto));
            if(usuario.getFriendlist().size()==0)
                usuarioService.addFriend(usuario,amigo);

            for(int i = 0; i<usuario.getFriendlist().size(); i++){
                if(usuario.getFriendlist().get(i).getId()!=(amigo.getId())){
                    usuarioService.addFriend(usuario,amigo);

                }
            }

            res.redirect("/home");
            return "";

        });

        get("/home", (req, res) -> {
            Usuario usuario = req.session(true).attribute("usuario");
            StringWriter writer = new StringWriter();
            Map<String, Object> atr = new HashMap<>();
            Template template = configuration.getTemplate("Template/home.ftl");
            List<Comentario> comentarios = comentarioService.getComentarios();
            List<Megusta> megusta = megustaService.getMegusta();
            atr.put("usuario",usuario);
            atr.put("comentarios",comentarios);
            atr.put("megusta",megusta);
            template.process(atr, writer);
            return writer;
        });
        get("/logout", (req, res) -> {
            StringWriter writer = new StringWriter();
            Map<String, Object> atr = new HashMap<>();
            Session ses = req.session(true);
            ses.invalidate();
            res.removeCookie("user");
            res.redirect("/login");
            return writer;
        });

        post("/login", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("pass");

            Usuario usuario = usuarioService.login(username, password);
            String RememberMe = req.queryParams("RememberMe");
            if (usuario != null) {
                req.session(true);
                req.session().attribute("usuario", usuario);
                if (RememberMe != null) {
                    BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
                    textEncryptor.setPasswordCharArray("rajadura27".toCharArray());
                    res.cookie("/", "user",
                            textEncryptor.encrypt(username), (60 * 60 * 24 * 7), false, true);
                }
                res.redirect("/");
            } else {
                res.redirect("/login");
            }
            return "";
        });
        post("/newPost", (req, res) -> {
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("src/main/resources/Template/upload/temp/"));

            Part filePart = req.raw().getPart("myfile");

            try (InputStream inputStream = filePart.getInputStream()) {

                OutputStream outputStream = new FileOutputStream("src/main/resources/Template/upload/temp/" + filePart.getSubmittedFileName());
                IOUtils.copy(inputStream, outputStream);
                outputStream.close();
            } catch (FileNotFoundException e) {
                Usuario usuario = req.session(true).attribute("usuario");
                String descrip = req.queryParams("descripcion");
                Post post = new Post();
                post.setDescripcion(descrip);
                post.setUsuario(usuario);
                post.setImg("");
                postService.savePost(post);
                usuarioService.newPost(usuario, post);
                res.redirect("/home");
                return "";

            }


            Usuario usuario = req.session(true).attribute("usuario");
            String descrip = req.queryParams("descripcion");
            Post post = new Post();
            post.setDescripcion(descrip);
            post.setUsuario(usuario);
            post.setImg(filePart.getSubmittedFileName());
            postService.savePost(post);
            usuarioService.newPost(usuario, post);
            res.redirect("/home");
            return "";

        });

        post("/comentario/:post", (req, res) -> {
            Usuario usuario = req.session(true).attribute("usuario");
            String texto = req.queryParams("texto");
            String postid = req.params("post").replace(",", "");
            Long p = Long.parseLong(postid);
            Comentario comen = new Comentario();
            comen.setTexto(texto);
            comen.setUsuario(usuario);
            comen.setPost(postService.findPost(p));
            comentarioService.saveComentario(comen);
         //   postService.newComentario(postService.findPost(p), comen);
         //   int im = postService.findIndex(postService.findPost(p).getComentarios(), postService.findPost(p).getId());
            res.redirect("/home");
            return "";

        });

        post("/signup", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            String nombre = req.queryParams("nombre");
            String apellido = req.queryParams("apellido");
            String fechaNacimiento = req.queryParams("birthDate");
            String lugarNacimiento = req.queryParams("lugarNac");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNac = sdf.parse(fechaNacimiento);

            if (usuarioService.checkUser(username) != null) {
                System.out.println(usuarioService.checkUser(username).getUsername());
                res.redirect("/signup?error=1");
            } else {
                Usuario usuario = new Usuario();
                usuario.setNombre(nombre);
                usuario.setUsername(username);
                usuario.setPassword(password);
                usuario.setFechaNac(fechaNac);
                usuario.setApellido(apellido);
                usuario.setLugarNac(lugarNacimiento);
                usuario.setWall(new ArrayList<>());

                usuarioService.saveUser(usuario);
                req.session(true);
                req.session().attribute("usuario", usuario);
                res.redirect("/");
                return "";
            }

            return "";
        });

        post("/comentario/:id/like", (req, res) -> {
            Usuario usuario = req.session(true).attribute("usuario");
            String comentarioId = req.params("id").replace(",", "");
            Long idComentario = Long.parseLong(comentarioId);
            Megusta megusta = new Megusta();
            megusta.setUsuario(usuario);
            megusta.setMegusta(true);
            //Megusta.setTiempo(getFechaActual());
            Comentario comentario = comentarioService.findComentario(idComentario);
            megusta.setComentario(comentario);
            Megusta re = megustaService.ComentarioLikeCheck(usuario,comentario);

            if(re == null){
                megustaService.guardarLike(megusta);
                    res.redirect("/home");

            }else{
                megustaService.deleteMegusta(re);

                if(!re.isMegusta())
                    megustaService.updateMegusta(re,true);

                        res.redirect("/home");

            }

            return "";
        });



        post("/post/:id/like", (req, res) -> {
            Usuario usuario = req.session(true).attribute("usuario");
            String postId = req.params("id").replace(",", "");
            Long idPost = Long.parseLong(postId);
            Megusta megusta = new Megusta();
            megusta.setUsuario(usuario);
            megusta.setMegusta(true);
          //  megusta.setTiempo(getFechaActual());
            Post post = postService.findPost(idPost);
            megusta.setPost(post);
            Megusta re = megustaService.CheckLikePost(usuario,post);

            if(re == null){
                megustaService.guardarLike(megusta);
                res.redirect("/home");

            }else{
                megustaService.deleteMegusta(re);

                if(!re.isMegusta())
                    megustaService.updateMegusta(re,true);

                res.redirect("/home");

            }

            return "";
        });



    }
    public static void smartCombine(List<Post> first, List<Post> second) {
        for(Post num : second) {
            if(!first.contains(num)) {
                first.add(num);
            }
        }
    }
}