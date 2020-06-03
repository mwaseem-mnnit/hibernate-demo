import javax.management.*;
import java.lang.management.ManagementFactory;

public class MBeanExample {
    public static void main(String[] args) {

        System.out.println("This is basic JMX tutorial");

        try {
            ObjectName objectName = new ObjectName("com.baeldung.tutorial:type=basic,name=game");
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            server.registerMBean(new Game(), objectName);
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException |
                MBeanRegistrationException | NotCompliantMBeanException e) {
            e.printStackTrace();
        }

        System.out.println("Registration for Game mbean with the platform server is successfull");
        System.out.println("Please open jconsole to access Game mbean");

        while (true) {
            // to ensure application does not terminate
        }
    }

    public static class Game implements GameMBean {
        private String playerName;

        @Override
        public void playFootball(String clubName) {
            System.out.println(this.playerName + " playing football for " + clubName);
        }

        @Override
        public String getPlayerName() {
            System.out.println("Return playerName " + this.playerName);
            return playerName;
        }

        @Override
        public void setPlayerName(String playerName) {
            System.out.println("Set playerName to value " + playerName);
            this.playerName = playerName;
        }

    }
    public interface GameMBean {

        public void playFootball(String clubName);

        public String getPlayerName();

        public void setPlayerName(String playerName);

    }

}