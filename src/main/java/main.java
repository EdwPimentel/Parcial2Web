import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class main {
  //  ClientConfiguration streamConfig = new ClientConfiguration();
  public static void main(String[] args){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("perunit");
    EntityManager em = emf.createEntityManager();
  }
 //   StreamClient streamClient = new StreamClientImpl(streamConfig, "jmcze9f5wmsr", "dpujgvytx5jt2yzw6xc9zesnvdcg754j3tzym3qbm7ncgtpyy8yt4ztrqfkgghjg");
}
