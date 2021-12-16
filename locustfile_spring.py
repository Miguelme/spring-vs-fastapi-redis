from locust import HttpUser, task, between

class SpringAPIUser(HttpUser):
    wait_time = between(1, 5)
    host = "http://localhost:8001"
    keys_to_generate = 10
    
    @task
    def spring_api(self):    
        self.client.post("/template", json=self.keys)
        
    def on_start(self):
        self.client.get(f"/template/generate/{SpringAPIUser.keys_to_generate}")
        self.keys = self.client.get("/template").json()[:50]