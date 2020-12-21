import java.security.PublicKey;

public class User    {
        private static  String id_log;

        public static String get_Id_Log(){
            return id_log;

        }
        public static  void set_Id_Log(String id_log){
            User.id_log = id_log;
        }
}
