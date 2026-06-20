# 🏥 NovaCare Clinic Management System

## 📖 Description

NovaCare Clinic Management System est une application de gestion de clinique développée en **JavaFX** selon l'architecture **MVC** avec une base de données **MySQL**.

L'application permet de gérer efficacement les patients, les médecins, les rendez-vous et les utilisateurs, tout en intégrant des fonctionnalités innovantes telles qu'une **banque de sang intelligente** et un **système d'alerte d'urgence**.

---

# ✨ Fonctionnalités

* 🔐 Authentification (Login / Logout)
* 👥 Gestion des utilisateurs (Administrateur / Secrétaire)
* 🩺 Gestion complète des patients (CRUD)
* 👨‍⚕️ Gestion complète des médecins (CRUD)
* 📅 Gestion des rendez-vous
* 📊 Tableau de bord avec statistiques en temps réel
* 🔎 Recherche et filtrage des données
* 🩸 Banque de sang intelligente avec recherche de donneurs compatibles selon le groupe sanguin
* 📍 Filtrage des donneurs par ville
* 📅 Gestion de la date du dernier don de sang
* 🚨 Module d'alerte d'urgence permettant à la secrétaire d'envoyer une notification prioritaire au médecin (simulation d'une notification mobile)
* ✅ Confirmation avant suppression
* 🎨 Interface graphique moderne développée avec JavaFX

---

# 🛠 Technologies utilisées

* Java 21
* JavaFX
* MySQL
* JDBC
* Architecture MVC
* CSS JavaFX
* IntelliJ IDEA
* Git & GitHub

---

# 🗂 Architecture du projet

* **controller** : logique métier
* **dao** : accès aux données
* **model** : classes métier
* **utils** : connexion base de données et utilitaires
* **view** : interfaces JavaFX

---

# 👨‍💻 Fonctionnalités innovantes

### 🩸 Banque de sang intelligente

Le système recherche automatiquement les donneurs compatibles selon les règles de compatibilité des groupes sanguins et permet un filtrage par ville et disponibilité.

### 🚨 Alerte urgence

La secrétaire peut créer une alerte médicale urgente en renseignant la nature de l'urgence et un message. L'application simule l'envoi d'une notification sur le téléphone du médecin afin d'améliorer la réactivité en cas d'urgence.

---

# 📷 Captures d'écran

* Interface de connexion
<img width="960" height="505" alt="Capture d&#39;écran 2026-06-18 172524" src="https://github.com/user-attachments/assets/0b565f7b-3d45-4961-8e2a-84f667c5c0e1" />

* Tableau de bord<img width="960" height="505" alt="Capture d&#39;écran 2026-06-18 172936" src="https://github.com/user-attachments/assets/739bf38e-8a58-4d75-b00d-3392b0a6f5ff" />

* Gestion des patients<img width="960" height="504" alt="Capture d&#39;écran 2026-06-18 173004" src="https://github.com/user-attachments/assets/d32c7027-5344-4ef9-bff3-7cd7e3b5f54a" />

* Gestion des médecins<img width="955" height="504" alt="Capture d&#39;écran 2026-06-18 173047" src="https://github.com/user-attachments/assets/fc455195-3ddc-48af-86f3-13dce0245db1" />

* Gestion des rendez-vous<img width="960" height="505" alt="Capture d&#39;écran 2026-06-18 173100" src="https://github.com/user-attachments/assets/8f52d469-7ea4-46e8-8eac-42e9b87146be" />

* Banque de sang intel
ligente<img width="960" height="505" alt="Capture d&#39;écran 2026-06-18 173115" src="https://github.com/user-attachments/assets/c52c5435-c12e-412e-ad3e-5b6ffbc75f26" />

* Alerte urgence<img width="715" height="503" alt="Capture d&#39;écran 2026-06-18 173329" src="https://github.com/user-attachments/assets/81897095-0036-4382-ac39-36da75636c68" />

---
# 🎥 Démonstration

Une vidéo de démonstration est disponible dans le dossier :

```
demo/Video Project.mp4
```
## 📄 Rapport du projet

Le rapport complet du projet est disponible dans le dossier **rapport/**.

📄 `rapport/Rapport_Gestion_Clinique_JavaFX.pdf`

---
---
# 🚀 Installation

1. Cloner le projet

```bash
git clone https://github.com/meryemjaber/Clinic-Management-JavaFX.git
```

2. Ouvrir le projet avec IntelliJ IDEA

3. Configurer la base de données MySQL

4. Exécuter :

```
MedicalTreatmentApp.java
```
---

# 🚀 Auteur

**Meryem Jaber/Alae Assakal**

Projet académique réalisé dans le cadre du module JavaFX.
