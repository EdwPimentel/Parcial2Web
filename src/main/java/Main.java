import freemarker.template.Configuration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Template;
import modelo.Comentario;
import modelo.Post;
import modelo.Usuario;
import org.jasypt.util.text.BasicTextEncryptor;
import serivicios.ComentarioService;
import serivicios.PostService;
import serivicios.UsuarioService;
import spark.Request;
import spark.Session;
import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        File uploadDir = new File("upload/temp");
        uploadDir.mkdir();// create the upload directory if it doesn't exist
        staticFiles.externalLocation("upload/temp");


        UsuarioService usuarioService = new UsuarioService();
        PostService postService = new PostService();
        ComentarioService comentarioService = new ComentarioService();
        staticFiles.location("/Template");
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
        get("/home", (req, res) -> {
            Usuario usuario = req.session(true).attribute("usuario");
            StringWriter writer = new StringWriter();
            Map<String, Object> atr = new HashMap<>();
            Template template = configuration.getTemplate("Template/home.ftl");
            atr.put("usuario", usuario);
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
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/upload/temp"));
            Part filePart = req.raw().getPart("myfile");
            String fileName= filePart.getSubmittedFileName();
            if(filePart!=null) {
                try (InputStream inputStream = filePart.getInputStream()) {
                    OutputStream outputStream = new FileOutputStream("/upload/temp/" + filePart.getSubmittedFileName());
                    IOUtils.copy(inputStream, outputStream);
                    outputStream.close();
                }
            }

            Usuario usuario = req.session(true).attribute("usuario");
            String descrip = req.queryParams("descripcion");
            Post post = new Post();
            post.setDescripcion(descrip);
            post.setUsuario(usuario);
            post.setImg(fileName);
            postService.savePost(post);
            usuarioService.newPost(usuario, post);
            res.redirect("/home");
            return "";

        });

        post("/comentario/:post", (req, res) -> {

            Usuario usuario = req.session(true).attribute("usuario");
            String texto = req.queryParams("texto");
            Long p = Long.parseLong(req.params("post"));
            Comentario comen = new Comentario();
            comen.setTexto(texto);
            comen.setUsuario(usuario);
            comentarioService.saveComentario(comen);
            postService.newComentario(postService.findPost(p), comen);
            int im = postService.findIndex(postService.findPost(p).getComentarios(), postService.findPost(p).getId());
            usuario.getWall().get(im).getComentarios().add(comen);
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

    /*    post("/upload", (req, res) -> {
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("upload/temp"));
            Part filePart = req.raw().getPart("myfile");
            if(filePart!=null) {
                try (InputStream inputStream = filePart.getInputStream()) {
                    OutputStream outputStream = new FileOutputStream("upload/temp/" + filePart.getSubmittedFileName());
                    IOUtils.copy(inputStream, outputStream);
                    outputStream.close();
                }
            }
            res.redirect("/home");
            return "";
        });*/

        }

}
