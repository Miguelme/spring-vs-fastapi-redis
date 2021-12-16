from locust import HttpUser, task, between

class FastAPIUser(HttpUser):
    wait_time = between(1, 5)
    host = "http://localhost:8000"
    keys_to_generate = 10
    
    @task
    def fast_api(self):
        result = self.client.post("/keys", json=self.keys)
        
    def on_start(self):
        self.client.get(f"/generate/{FastAPIUser.keys_to_generate}")
        self.keys = self.client.get("/keys").json()[:50]