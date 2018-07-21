import freemarker.template.Configuration;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.get;
import static spark.Spark.staticFiles;
import freemarker.template.Template;

public class Main {
  public static void main(String[] args){
    staticFiles.location("/Template");
    Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
    configuration.setClassForTemplateLoading(Main.class, "/");
      get("/", (req, res) -> {
          StringWriter writer = new StringWriter();
          Map<String, Object> atr = new HashMap<>();
          Template template = configuration.getTemplate("Template/login.ftl");
          template.process(atr,writer);
          return writer;
      });
  }
}
