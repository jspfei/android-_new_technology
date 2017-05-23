package net.tt.androidserver.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.tt.androidserver.R;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JavaCmdShellActivity extends Activity {
    private TextView outputView;
    private Button localRunButton;
    private EditText localPathEdit;
    private Handler handler = new Handler();
    private EditText urlEdit;
    private Button remoteRunButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_cmd_shell);

        outputView = (TextView)findViewById(R.id.outputView);
        localPathEdit = (EditText)findViewById(R.id.localPathEdit);
        localRunButton = (Button)findViewById(R.id.localRunButton);
        localRunButton.setOnClickListener(onLocalRunButtonClick);
    }


    private View.OnClickListener onLocalRunButtonClick = new View.OnClickListener() {
        public void onClick(View v) {
            String output = exec(localPathEdit.getText().toString());
            output(output);
//			  try {
//
//			     // Process process = Runtime.getRuntime().exec(localPathEdit.getText().toString());
//
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
        }
    };


    // Executes UNIX command.
    private String exec(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            int read;
            char[] buffer = new char[4096];
            StringBuffer output = new StringBuffer();
            while ((read = reader.read(buffer)) > 0) {
                output.append(buffer, 0, read);
            }
            reader.close();
            process.waitFor();
            return output.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void download(String urlStr, String localPath) {
        try {
            URL url = new URL(urlStr);
            HttpURLConnection urlconn = (HttpURLConnection)url.openConnection();
            urlconn.setRequestMethod("GET");
            urlconn.setInstanceFollowRedirects(true);
            urlconn.connect();
            InputStream in = urlconn.getInputStream();
            FileOutputStream out = new FileOutputStream(localPath);
            int read;
            byte[] buffer = new byte[4096];
            while ((read = in.read(buffer)) > 0) {
                out.write(buffer, 0, read);
            }
            out.close();
            in.close();
            urlconn.disconnect();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void output(final String str) {
        Runnable proc = new Runnable() {
            public void run() {
                outputView.setText(str);
            }
        };
        handler.post(proc);
    }
}
