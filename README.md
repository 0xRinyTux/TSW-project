# BuyandPlay

## Panoramica del Progetto
BuyandPlay è un'applicazione web progettata per gestire l'acquisto e la vendita di videogiochi. La piattaforma permette agli utenti di sfogliare il catalogo, acquistare giochi e gestire i propri ordini, mentre gli amministratori possono gestire il catalogo e i dati degli utenti.

## Funzionalità
- Registrazione e autenticazione utenti
- Navigazione del catalogo giochi
- Gestione del carrello
- Elaborazione ordini e pagamento
- Pannello admin per la gestione di catalogo e utenti
- Gestione di sessioni e cookie

## Tecnologie Utilizzate
- Java (Servlet, JSP)
- HTML, CSS, JavaScript
- Maven
- Docker (cross building)
- MySQL (database)

## Struttura del Progetto
```
BuyandPlay/
├── src/
│   └── main/
│       ├── java/
│       │   ├── controller/   # Servlet per la gestione delle richieste
│       │   └── model/        # JavaBean e classi DAO
│       └── webapp/           # Risorse web (HTML, JSP, CSS, JS, immagini)
├── pom.xml                   # Configurazione Maven
├── Dockerfile                # Supporto Docker
├── docker-compose.yml        # Supporto Docker Compose
```

## Come Eseguire
L'unico modo supportato per buildare ed eseguire il progetto è tramite Docker Compose.

1. **Eseguire con Docker (Easy):**
   ```bash
   docker-compose up --build
   ```

Questo comando costruirà e avvierà automaticamente tutti i servizi necessari (applicazione e database) tramite i container Docker.


---

## Concorrenza di Mercato
- Il principale rivale di BuyandPlay è **Steam**, la piattaforma di distribuzione digitale leader per PC e console, che offre infrastrutture di content delivery, aggiornamenti automatici, gestione licenze e servizi social integrati.

## Architettura e Dettagli Tecnici
- **Pattern MVC**: il package `controller` contiene servlet per il routing e la gestione delle richieste HTTP, mentre `model` implementa JavaBean e DAO per l’accesso a MySQL tramite JDBC e PreparedStatement.
- **View**: le pagine JSP sono collocate in `WEB-INF/results` e servono la parte di presentazione; le risorse statiche (CSS, JavaScript, immagini) sono in `webapp/css`, `webapp/js` e `webapp/imagesDB`.
- **Persistenza**: gli script SQL (`db-init/schema.sql`, `db-init/db_buy.sql`, `db-init/insert.sql`) definiscono schema e dati iniziali, caricati automaticamente all’avvio del container MySQL.
- **Build e Deployment**:
  - Maven con wrapper (`.mvn/wrapper`) gestisce le dipendenze e il ciclo di vita (compilazione, packaging WAR).
  - Dockerfile e `docker-compose.yml` orchestrano due servizi: un container Tomcat per l’applicazione e un container MySQL per il database, con volume per lo script di inizializzazione.
- **Gestione Sessione e Security**:
  - `HttpSession` e cookie per il login persistente; le password sono hashate con SHA-256.
  - Validazione dei parametri utente nei DAO e uso di PreparedStatement per prevenire SQL injection.
- **Funzionalità Avanzate**:
  - Carrello persistente in sessione, flusso di checkout con creazione di fatture e generazione di chiavi di licenza.
  - Pannello admin dedicato (inserimento, modifica e rimozione giochi).
  - Servizi di ricerca full-text semplificata e selezione dei bestseller.

---

## Autore
Rinaldo Perna