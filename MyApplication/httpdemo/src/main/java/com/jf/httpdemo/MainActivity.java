package com.jf.httpdemo;

import android.net.http.HttpResponseCache;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 *
 * 在Android上发送HTTP请求的方式一般有两种，HttpURLConnection和HttpClient，现在先学习下
 HttpURLConnection的用法。
 首先需要获取到HttpURLConnection的实例，一般只需new 出一个URL对象，并传入目标网络的地址，然后
 调用一下openConnection()方法即可，如下所示：
 URL URL=new URL("http://www.baidu.com");
 HttpURLConnection connection=( HttpURLConnection)url.openConnection();
 得到了 HttpURLConnection的实例之后，我们可以设置一下HTTP请求所使用的方法。常用的方法主要有两个，
 get和post。get表示希望从服务器那里获取数据，而post则表示提交数据给服务器。写法如下：
 connection.setRequestMethod("GET");
 接下来就可以进行一些自由的定制了，比如设置连接超时，读取超时的毫秒数，以及服务器希望得到的一些消息头等。这部分内容根据自己的实际情况进行编写，示例如下：
 connection.setConnectionTimeout(8000);
 connection.setReadTimeout(8000);
 之后调用getInputStream()方法就可以获取到服务器返回的输入流了，剩下的任务就是对输入流进行读取，如下所示：
 InputStream in=connection.getInputStream();
 最后可以调用disconnect()方法将这个HTTP连接关闭掉，如下所示：
 connection.disconnection();
 下面通过一个具体的例子来熟悉下HttpURLConnection的用法。新建NetworkTest项目，首先修改activit_main.xml中的代码，
 *
 *
 * */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int SHOW_RESPONSE =0;
    private static String TAG = "MainActivity";
    private Button sendRequest = null;
    private TextView responseText = null;
    private Handler handler = new Handler(){
      @Override
        public void handleMessage(Message msg){
          super.handleMessage(msg);
          switch (msg.what){
              case SHOW_RESPONSE:
                  String response = (String) msg.obj;
                  //在这里进入UI操作，将结果显示到界面上
                  responseText.setText(response);
                  break;
              default:
                  break;
          }
      }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendRequest = (Button)findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.i("HACK-TAG",TAG + "onClick(View v)!");
        if(v.getId()==R.id.send_request){
           // sendRequestWithHttpURLConnection();
            setRequestWithHttpClient();
        }
    }

    private void sendRequestWithHttpURLConnection() {
        //开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;
                try{

                    URL url = new URL("http://www.baidu.com");
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    /**
                     *  post提交
                     connection.setRequestMethod("POST");
                     DataOutputStream out=new DataOutputStream(connection.getOutputStream());
                     out.writeBytes("username=admin&password=123456");
                     * */

                    //下面对获取到的输入流进行读取
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null){
                        response.append(line);
                    }
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    message.obj = response.toString();
                    handler.sendMessage(message);

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(connection !=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void setRequestWithHttpClient(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    HttpClient httpClient=new DefaultHttpClient() ;
                    HttpGet httpGet=new HttpGet("http://www.baidu.com");
                    HttpResponse httpResponse=httpClient.execute(httpGet);
                    if(httpResponse.getStatusLine().getStatusCode()==200){
                        //请求和响应都成功了
                        HttpEntity entity=httpResponse.getEntity();
                        String response= EntityUtils.toString(entity,"utf-8");
                        Message message=new Message();
                        message.what=SHOW_RESPONSE;
                        //将服务器返回的结果存放到Message中
                        message.obj=response.toString();
                        handler.sendMessage(message);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
