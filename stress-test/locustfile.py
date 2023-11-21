from locust import HttpUser, task, between

class WebUser(HttpUser):
    wait_time = between(1, 3) # tempo de espera entre requisições em segundos
    host = "http://localhost:8765" # URL da aplicação web

    @task(1)
    def list_tasks(self):
        self.client.get("/pagamentos")

    @task(1)
    def create_task(self):
          self.client.post("/pagamentos", json={"valor": 1000, "nome": "Arthur", "email": "art@gmail.com", "cartao_id": 1})

    @task(1)
    def create_task2(self):
          self.client.post("/pagamentos/cartao", json={"nome": "Arthur", "numero": "1234567890123456", "dataExpiracao":"04/29", "codigoSeguranca":"123", "tipoDePagamento":"CREDITO"})


    @task(1)
    def create_task2(self):
          self.client.post("/pedido", json={"pessoa_id":1 , "produtos_id": [1,2]})

    @task(1)
    def create_task2(self):
          self.client.post("/produto", json={"nome": "agua", "valor": 100})

    @task(1)
    def create_task2(self):
          self.client.post("/pessoa", json={"nome": "Arthur", "email": "arthur@gmail.com", "cpf":"23213131"})

    @task(1)
    def create_task2(self):
          self.client.get("/pedido")