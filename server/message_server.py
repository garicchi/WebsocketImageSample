import tornado.ioloop
import tornado.web
import tornado.websocket
import base64

cl = []
class MessageServer(tornado.websocket.WebSocketHandler):

    def check_origin(self, origin):
        return True
    
    def open(self):
        if self not in cl:
            cl.append(self)

    def on_message(self,message):
        for client in cl:
            print('receive!')
            file = base64.b64decode(message)
            with open('file.png','wb') as f:
                f.write(file)

            #print(message)
            #client.write_message(message)
    
    def on_close(self):
        if self in cl:
            cl.remove(self)

application = tornado.web.Application([(r'/ws',MessageServer)])

if __name__ =="__main__":
    application.listen(8080)
    tornado.ioloop.IOLoop.current().start()