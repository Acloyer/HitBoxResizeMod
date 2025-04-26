# AutoGG Mod for Minecraft 1.8.9

## 📦 Description
**AutoGG** is a simple Minecraft 1.8.9 Forge mod that allows you to increase player hitbox size (reach area) using an easy in-game command.  
Useful for improving visibility of player hitboxes, especially in PvP environments.

✅ **Client-side only** — does not require installation on the server.

---

## 🛠️ Features
- Adjust player hitbox scaling directly in-game.
- Simple command to change the multiplier on the fly:
/autogg [multiplier]

makefile
Copy
Edit
Example:
/autogg 1.5

markdown
Copy
Edit
This sets the hitbox multiplier to **1.5×**.

- Hitbox rendering (ESP-style visualization).
- Extended reach calculation without server-side modification.
- Lightweight and easy to use.

---

## 💾 Installation

1. Download and install **Minecraft Forge 1.8.9**.
2. Download the latest **AutoGG.jar** from the [Releases](https://github.com/yourusername/AutoGG/releases) page.
3. Place the `.jar` file into your:
.minecraft/mods/

yaml
Copy
Edit
4. Launch Minecraft using the Forge profile.

---

## 📌 Commands

| Command                 | Description                               |
|-------------------------|-------------------------------------------|
| `/autogg [value]`        | Sets the hitbox multiplier (float). Example: `/autogg 1.3` |
| `/autoggadd [value]`     | Adds the given value to the current multiplier. Example: `/autoggadd 0.2` |

The default multiplier is **1.0** (normal hitbox). Increase it carefully to avoid rendering glitches.

---

## ⚠️ Notes
- This mod modifies hitbox detection and may not work correctly on servers with strong anti-cheat.
- Works only with **Minecraft 1.8.9 Forge**.

---

## 👤 Credits
Created by **Acloyer (Rafig)**.

---

## 📃 License
This project is licensed under the Forge mod distribution terms.  
Feel free to use or modify for personal use. Commercial use is not allowed without permission.
