Here is your clean Markdown note you can save and study later 👇

# 📌 How form data goes from HTML to Spring Boot

## 🔹 1. HTML (Collects Data)

<input id="email">
<button onclick="login()">Submit</button>

HTML only collects user input.  
It does not send data by itself in modern API style.

---

## 🔹 2. JavaScript (Sends Data)

function login() {
  fetch("/login", {
    method: "POST",
    headers: {"Content-Type":"application/json"},
    body: JSON.stringify({
      email: document.getElementById("email").value,
      password: "123"
    })
  });
}

👉 JavaScript is responsible for sending the data to backend.

---

## 🔹 3. Spring Controller (Receives Data)

@PostMapping("/login")  
public AuthResponse login(@RequestBody AuthRequest req)

@PostMapping → receives request  
@RequestBody → reads JSON  

---

## 🔹 4. Spring Converts JSON to Java

JSON sent:

{ "email": "test@gmail.com" }

Converted into:

req.email = "test@gmail.com";

---

## 🔁 Full Flow

HTML → JavaScript → Spring Controller → Java Object
“Read the JSON from the request body and convert it into a Java object.”

---

## 🧠 Responsibility Table

Part | Responsibility  
HTML | Collects input  
JavaScript | Sends data  
@PostMapping | Receives request  
@RequestBody | Reads JSON  
Controller | Processes data  

---

## ⚠️ Important Note

If you use normal HTML form without JS:

<form method="post" action="/login">

Then Spring must use:

@RequestParam  

because browser sends form data, not JSON.

---

## ✅ One-line Memory

HTML collects, JavaScript sends, Spring receives.

---

## 📘 Extra Topics

If you want, I can also prepare markdown notes for:

@GetMapping vs @PostMapping  
Controller vs Service vs Repository  
Entity vs DTO  
Full login flow diagram
