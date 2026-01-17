# 🌍 Map & Routing Technology Used in This Project

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
