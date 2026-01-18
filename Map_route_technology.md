# 🌍 Map & Routing Technology Used in This Project
https://chatgpt.com/c/696b765f-aa44-8331-a1e1-db37690eda7b 
My link of gpt


In this project, we used a **free and open-source mapping stack** instead of Google Maps.

---

## 🗺 Map Provider  
**OpenStreetMap (OSM)**  
OpenStreetMap is a community-driven, open-source world map database that provides free geographic data.

---

## 📌 Map Visualization Library  
**Leaflet.js**

Leaflet is a lightweight JavaScript library used to display interactive maps in web applications. It is responsible for:

- Rendering the map  
- Zoom and pan controls  
- Drawing routes (polylines)  
- Displaying markers  

---

## 📍 Location Search (Geocoding)  
**Nominatim API (OpenStreetMap Geocoder)**  

Nominatim converts place names into geographic coordinates:

"Munich" → latitude & longitude

It is used to transform user-entered locations into coordinates for routing.

---

## 🚗 Route & Distance Calculation  
**OSRM (Open Source Routing Machine) Public API**

OSRM calculates:

- Driving route  
- Distance in meters  
- Route geometry for drawing on the map  

---

## ⛽ Fuel Cost Calculation  

Fuel cost is calculated on the frontend using:

Fuel Cost = (Distance / 100) × Fuel Consumption × Fuel Price

Where:
- Distance is obtained from OSRM  
- Fuel consumption and fuel price are entered by the user  

---

## 🔧 Technology Stack Summary

| Purpose | Technology |
|-------|-----------|
| Map tiles | OpenStreetMap |
| Map rendering | Leaflet.js |
| Location → Coordinates | Nominatim API |
| Routing & distance | OSRM API |
| Fuel calculation | JavaScript |

---

## ✅ Reason for Choosing This Stack

- No API key required  
- No billing required  
- Fully open source  
- Easy to integrate  
- Ideal for academic and student projects  
- Production-ready for real-world applications  

---

## 🧠 One-line Project Description

This project uses OpenStreetMap with Leaflet.js for map visualization, Nominatim for geocoding, and OSRM for routing and distance calculation, enabling automatic fuel cost estimation based on travel distance.

## ⛽ Fuel Cost Calculation Based on Distance

Fuel cost in this system is **fully based on travel distance**.

### 🔹 How the calculation works

1. The route distance is obtained from the routing service (OSRM).
2. The user provides:
   - Fuel consumption (L/100km)
   - Fuel price (€/L)

3. The system calculates fuel cost using:

Fuel Cost = (Distance / 100) × Fuel Consumption × Fuel Price

---

### 🔹 Example

If:
- Distance = 200 km  
- Fuel consumption = 7 L / 100 km  
- Fuel price = €1.80  

Then:

Fuel Cost = (200 / 100) × 7 × 1.80  
Fuel Cost = €25.20

---

### 🔹 Key Points

- Longer distance → higher fuel cost  
- Shorter distance → lower fuel cost  
- Fuel cost updates automatically when route changes  
- No external fuel price API is used  
- Calculation is transparent and user-controlled  

---

### 🔹 Project Description Line

Fuel cost in this system is dynamically calculated based on route distance, user-defined fuel consumption, and current fuel price, ensuring realistic and distance-dependent cost estimation.
