package net.tt.twoactivitytest;

import java.util.Map;

/**
 * Created by admin on 2017/5/15.
 */

public class WebServer extends NanoHTTPD {
    public WebServer() {
        super(8081);
    }
    @Override
    public Response serve(String uri, Method method,
                          Map<String, String> header,
                          Map<String, String> parameters,
                          Map<String, String> files) {
        String answer = "Hello World";
//            try {
//                // Open file from SD Card
//                File root = Environment.getExternalStorageDirectory();
//                FileReader index = new FileReader(root.getAbsolutePath() + "/CodeGo.net");
//                BufferedReader reader = new BufferedReader(index);
//                String line = "";
//                while ((line = reader.readLine()) != null) {
//                    answer += line;
//                }
//            } catch (IOException ioe) {
//                Log.w("Httpd", ioe.toString());
//            }

        return new NanoHTTPD.Response(answer);
    }
}
