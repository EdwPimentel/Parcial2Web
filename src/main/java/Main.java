import freemarker.template.Configuration;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import freemarker.template.Template;
import modelo.Usuario;
import org.jasypt.util.text.BasicTextEncryptor;
import serivicios.UsuarioService;

public class Main {
  public static void main(String[] args){
      UsuarioService usuarioService = new UsuarioService();
    staticFiles.location("/Template");
    Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
    configuration.setClassForTemplateLoading(Main.class, "/");
      get("/", (req, res) -> {
          StringWriter writer = new StringWriter();
          Map<String, Object> atr = new HashMap<>();
          Template template = configuration.getTemplate("Template/signup.ftl");
          template.process(atr,writer);
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
                  textEncryptor.setPasswordCharArray("mangekyouSharingan42".toCharArray());
                  res.cookie("/", "user",
                          textEncryptor.encrypt(username), (60*60*24*7), false, true);
              }
              res.redirect("/");
          } else {
              res.redirect("/login");
          }
          return "";



      });
  }
}
