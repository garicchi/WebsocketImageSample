package com.garicchi.client;

import java.io.File;
import java.io.FileInputStream;
import	java.net.URI;
import	java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Base64;
import	javax.websocket.ClientEndpointConfig;
import	javax.websocket.ContainerProvider;
import	javax.websocket.DeploymentException;
import	javax.websocket.Endpoint;
import	javax.websocket.EndpointConfig;
import	javax.websocket.MessageHandler;
import	javax.websocket.Session;
import	javax.websocket.WebSocketContainer;

public class WebsocketMessenger extends Endpoint
{

    public String URL;
    public Session session;
    public WebsocketMessenger()																							//@<BlockInfo>jp.vstone.block.func.constructor,16,16,432,16,False,4,@</BlockInfo>
    {
        //@<OutputChild>
        URL="";																											//@<BlockInfo>jp.vstone.block.variable,80,16,80,16,False,3,break@</BlockInfo>
        //@<EndOfBlock/>
		/*RecogResult recogresult*/;																					//@<BlockInfo>jp.vstone.block.variable,160,16,160,16,False,2,break@</BlockInfo>
        //@<EndOfBlock/>
        session=null;																									//@<BlockInfo>jp.vstone.block.variable,272,16,272,16,False,1,break@</BlockInfo>
        //@<EndOfBlock/>
        //@</OutputChild>
    }																													//@<EndOfBlock/>

    //@<Separate/>
    public void connect(String URL)	{

        //@<OutputChild>
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();										//@<BlockInfo>jp.vstone.block.freeproc,640,400,640,400,False,11,@</BlockInfo>
        ClientEndpointConfig config = ClientEndpointConfig.Builder.create().build();

        try {

            this.session = container.connectToServer(new Endpoint() {
                @Override
                public void onOpen(Session session, EndpointConfig endpointConfig) {
                    
                }
            }, config, URI.create(URL));


        } catch (DeploymentException e) {

            e.printStackTrace();
        } catch (IOException e)
        {e.printStackTrace();}
        //@<EndOfBlock/>
        //@</OutputChild>

    }																													//@<EndOfBlock/>

    //@<Separate/>
    public void onOpen(Session session,EndpointConfig eptCfg) {															//@<BlockInfo>jp.vstone.block.func,432,288,768,288,False,17,@</BlockInfo>
        //throws SpeechRecogAbortException {
        //if(!GlobalVariable.TRUE) throw new SpeechRecogAbortException("default");


        session.addMessageHandler(new MessageHandler.Whole<String>() {													//@<BlockInfo>jp.vstone.block.freeproc,576,288,576,288,False,15,@</BlockInfo>

            public void onMessage(String message) {
                //@<EndOfBlock/>
                //GlobalVariable.sotawish.Say((String)message,MotionAsSotaWish.MOTION_TYPE_TALK,(int)11,(int)13,(int)11);			//@<BlockInfo>jp.vstone.block.talk.say,640,288,640,288,False,14,@</BlockInfo>
                //@<EndOfBlock/>
                //@<BlockInfo>jp.vstone.block.freeproc,704,288,704,288,False,13,@</BlockInfo>
            }

        });
        //@<EndOfBlock/>
        //@</OutputChild>

    }																													//@<EndOfBlock/>

    //@<Separate/>
    public void sendMessage(String message) {

        //@<OutputChild>
        try {																											//@<BlockInfo>jp.vstone.block.freeproc,128,288,128,288,False,18,@</BlockInfo>
            this.session.getBasicRemote().sendText(message);

        } catch (IOException e) {

            e.printStackTrace();
        }
        //@<EndOfBlock/>
        //@</OutputChild>

    }

    //@<EndOfBlock/>

    //@<Separate/>
    public void sendFile(String path) {

        //@<OutputChild>
        try {																											//@<BlockInfo>jp.vstone.block.freeproc,128,288,128,288,False,18,@</BlockInfo>
            File f=new File(path);
            int length=(int)f.length();
            byte[] buf=new byte[length];
            FileInputStream fs=new FileInputStream(path);
            fs.read(buf);
            fs.close();
            String encoded = Base64.getEncoder().encodeToString(buf);
            this.session.getBasicRemote().sendText(encoded);
        } catch (IOException e) {

            e.printStackTrace();
        }
        //@<EndOfBlock/>
        //@</OutputChild>

    }
}