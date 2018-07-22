import freemarker.template.Configuration;
import java.io.StringWriter;
import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Template;
import modelo.Usuario;
import org.jasypt.util.text.BasicTextEncryptor;
import serivicios.UsuarioService;
import spark.Session;

import static spark.Spark.*;

public class Main {
  public static void main(String[] args){
      UsuarioService usuarioService = new UsuarioService();
    staticFiles.location("/Template");
    Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
    configuration.setClassForTemplateLoading(Main.class, "/");


      get("/", (req, res) -> {
              Usuario usuario = req.session(true).attribute("usuario");
              if(usuario == null && req.cookie("user") != null){
                  BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
                  textEncryptor.setPasswordCharArray("rajadura27".toCharArray());
                  req.session(true);
                  req.session().attribute("usuario", usuarioService.checkUser(textEncryptor.decrypt(req.cookie("user"))));
                  }
              res.redirect("/login");
              return"";
      });
      get("/login",(req,res) -> {
              Usuario usuario = req.session(true).attribute("usuario");
              if(usuario!=null)
                  res.redirect("/home");
              StringWriter writer = new StringWriter();
              Map<String, Object> atr = new HashMap<>();
              Template template = configuration.getTemplate("Template/login.ftl");
              template.process(atr,writer);
              return writer;
      });

      get("/signup", (req, res) -> {

          StringWriter writer = new StringWriter();
          Map<String, Object> atr = new HashMap<>();
          Template template = configuration.getTemplate("Template/signup.ftl");
          template.process(atr,writer);
          return writer;
      });
      get("/home", (req, res) -> {
          Usuario usuario = req.session(true).attribute("usuario");
          StringWriter writer = new StringWriter();
          Map<String, Object> atr = new HashMap<>();
          Template template = configuration.getTemplate("Template/home.ftl");
          atr.put("usuario",usuario);
          template.process(atr,writer);
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
              if(RememberMe!=null){
                  BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
                  textEncryptor.setPasswordCharArray("rajadura27".toCharArray());
                  res.cookie("/", "user",
                          textEncryptor.encrypt(username), (60*60*24*7), false, true);
              }
              res.redirect("/");
          } else {
              res.redirect("/login");
          }
          return "";
      });


      post("/signup", (req, res) -> {
          String username = req.queryParams("username");
          String password = req.queryParams("password");
          String nombre = req.queryParams("nombre");
          String apellido = req.queryParams("apellido");
          String fechaNacimiento = req.queryParams("birthDate");
          String lugarNacimiento =req.queryParams("lugarNac");
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          Date fechaNac = sdf.parse(fechaNacimiento);

          if(usuarioService.checkUser(username)!=null){
              System.out.println(usuarioService.checkUser(username).getUsername());
              res.redirect("/inicio?invalid=1");
          }else{
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
  }
}
