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

## Autore
Rinaldo Perna

---
Questa documentazione è adatta per la presentazione all'esame universitario. Per ulteriori dettagli, consulta il codice sorgente e i commenti all'interno del progetto.