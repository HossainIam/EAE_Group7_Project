# We use controller because backend must handle HTTP requests
AuthController is used to handle authentication-related requests such as:
Login
Register / Signup
Logout
Token validation

👉 JSON → @RequestBody → Java object
👉 Java object → @RestController → JSON response

@RestController is NOT for showing HTML pages.

Purpose	Spring uses
- Show HTML page	@Controller
- Send/receive JSON	@RestController

🔹 6. @PutMapping
For updating data.
🔹 7. @DeleteMapping
For deleting data.
🔹 8. @RequestMapping
Base path for controller.

# Frontend sends	Spring uses
form-data	@RequestParam
JSON	@RequestBody
@RequestParam and @RequestBody do only one main job:
They read data that is already sent by the client.
They do not create data.
They do not convert JavaScript to JSON.
They only read what the client provided.

👉 @RequestParam and @RequestBody do only one main job:
They read data that is already sent by the client.
They do not create data.
They do not convert JavaScript to JSON.
They only read what the client provided.