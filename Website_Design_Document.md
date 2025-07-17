# Website Design Document

## 1. Overview

**Project:** BuyandPlay E-commerce Platform  
**Group Members:** Rinaldo Perna

Descrizione sintetica: BuyandPlay è una piattaforma web per la vendita di videogiochi digitali. Offre funzionalità di catalogo, ricerca, carrello, pagamenti e area amministratore.

---

## 2. Requirements Mapping

| Requisito                                                                      | Stato Implementazione |
|--------------------------------------------------------------------------------|-----------------------|
| Registrazione, login, logout utente                                            | ✔️                     |
| Carrello: aggiungi, rimuovi, modifica quantità, svuota                        | ✔️                     |
| Gestione ordini: conferma, storico ordini                                       | ✔️                     |
| Area amministratore: CRUD catalogo, visualizza ordini                           | ✔️                     |
| Persistenza dati in MySQL, pattern DAO                                          | ✔️                     |
| Architettura MVC: separazione servlet (controller) e bean/DAO (model)         | ✔️                     |
| Session/token per accesso e carrello                                            | ✔️                     |
| Controllo accessi su servlet e JSP                                              | ✔️                     |
| Validazione form client-side (regex, DOM)                                       | ✔️                     |
| Risorse statiche organizzate (scripts, styles, imagesDB)                       | ✔️                     |
| Responsive design                                                                | ✔️                     |
| Ambiente di produzione via Docker Compose                                       | ✔️                     |

\* In corso di completamento con regole aggiuntive e messaggi DOM.

---

## 3. Architectural Design

### 3.1. MVC Pattern
- **Model:** JavaBean e DAO in `src/main/java/model`
- **View:** JSP in `src/main/webapp/WEB-INF/results` + HTML/CSS/JS in `webapp`
- **Controller:** Servlet in `src/main/java/controller`

### 3.2. Request Flow
1. L’utente invia una richiesta HTTP (URL mapping Servlet).  
2. La Servlet recupera dati via DAO (utilizzo di DataSource/DriverManager).  
3. La Servlet popola bean e attributi di sessione/request.  
4. Viene richiamata la JSP di risposta (RequestDispatcher).  
5. La JSP renderizza HTML, include header/footer con `<jsp:include>`, usa CSS esterni e script per validazione.

---

## 4. Database Schema

```sql
-- Tabella Gioco
CREATE TABLE Gioco (
  titolo_gioco VARCHAR(100) PRIMARY KEY,
  prezzo_gioco DOUBLE,
  descrizione_gioco TEXT,
  anno_gioco VARCHAR(4),
  console VARCHAR(50),
  softwarehouse VARCHAR(100),
  url VARCHAR(255),
  in_sconto INT,
  prezzo_scont_gioco DOUBLE,
  genere VARCHAR(50),
  in_vendita INT,
  nome_immagine VARCHAR(100),
  numero_vendite INT
);

-- Tabella Cliente
CREATE TABLE Cliente (
  nickname VARCHAR(50) PRIMARY KEY,
  passwordCliente CHAR(64),
  email VARCHAR(100),
  nome VARCHAR(50),
  cognome VARCHAR(50),
  data_di_nascita DATE,
  numero_carta VARCHAR(20),
  indirizzo_fatturazione VARCHAR(255),
  tipo_carta VARCHAR(50)
);

-- Tabella Fattura
CREATE TABLE Fattura (
  id_fattura INT AUTO_INCREMENT PRIMARY KEY,
  nickname_cliente VARCHAR(50),
  data_acquisto DATE,
  tipo_carta VARCHAR(50),
  numero_carta VARCHAR(20),
  indirizzo_fatturazione VARCHAR(255)
);

-- Tabella Collocamento
CREATE TABLE Collocamento (
  id_collocamento INT AUTO_INCREMENT PRIMARY KEY,
  id_fattura INT,
  id_gioco VARCHAR(100)
);

-- Tabella Key
CREATE TABLE KeyTable (
  id_chiave INT AUTO_INCREMENT PRIMARY KEY,
  id_fattura INT,
  nome_gioco VARCHAR(100),
  key_code VARCHAR(255)
);
```

---

## 5. UI Structure & Navigation

- **Homepage (`index.html`):** elenca giochi più venduti (InitServlet)  
- **Search (`/SearchItem`):** form di ricerca + risultati in `risultato-ricerca.jsp`  
- **Dettaglio gioco (`/Dati`):** pagina `modifica-gioco.jsp` per admin / dettagli  
- **Carrello (`/Carrello`):** aggiunta, rimozione e visualizzazione  
- **Checkout (`/Acquisto`, `/Pagamento`):** dettaglio cliente + inserimento fattura  
- **Ordini (`/Ordini`):** storico ordini e download key  
- **Area Utente (`area-utente.jsp`):** modifica profilo (DatiServlet)  
- **Area Admin (`admin-home.html`, `controllo-giochi.jsp`):** CRUD catalogo  
- **Form validation:** script esterno `scripts/form-validation.js` con regex e DOM manipulation

---

## 6. Frontend Components

- **CSS:** in `webapp/css/`  
- **JavaScript:** in `webapp/scripts/` (validazione form, AJAX chiamate)  
- **Images:** in `webapp/imagesDB/` con naming coerente e `.jpeg`  
- **Layouts:** header/footer inclusi via `<jsp:include>`

---

## 7. Security & Access Control

- Autenticazione via session cookie (`username`).  
- Servlet di controllo `/ControlCookie`, `/VerificaLoginServlet`  
- Pagine admin protette tramite verifica token in sessione.  
- PreparedStatement per prevenire SQL injection.

---

## 8. Deployment

- **Docker Compose:** servizi applicazione + MySQL  
- **WAR packaging:** generato con Maven (`packaging>war`)  
- **Container orchestration:** `docker-compose up --build`

---

*Fine del documento.*
