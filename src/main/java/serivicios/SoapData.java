package serivicios;

import modelo.Post;
import modelo.Usuario;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class SoapData {
    UsuarioService usuarioService = new UsuarioService();
    PostService postService = new PostService();
    @WebMethod
    public List<Post> getPostsUsuario(String username,String password){
        List<Post> filtrar = usuarioService.login(username,password).getWall();
        if(filtrar==null){
            return null;
        }else{
            ArrayList<Post> ps = new ArrayList<>();
            for(Post p : filtrar){
                if(!p.getDescripcion().equalsIgnoreCase("n/a")){
                    Post post = new Post();
                    post.setId(p.getId());
                    post.setDescripcion(p.getDescripcion());
                    ps.add(post);
                }

            }
            return ps;
        }

    }

    @WebMethod
    public String crearPost(String username,String texto){
        Usuario u = usuarioService.checkUser(username);

            Post post = new Post();
            post.setDescripcion(texto);
            post.setImg("");
            post.setUsuario(u);
            postService.savePost(post);
            usuarioService.newPost(u,post);
            return "Post creado con exito";
    }
}
