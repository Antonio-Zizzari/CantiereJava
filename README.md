<div align="center">

# 🏗️ CantiereJava

**Gestionale desktop per un'impresa edile: personale, cantieri, squadre, rapporti esterni e report statistici.**

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/GUI-Swing-5382A1?style=for-the-badge)
![OOP](https://img.shields.io/badge/Paradigma-OOP-6DB33F?style=for-the-badge)

*Progetto universitario, Università degli Studi di Salerno*

</div>

---

## 📑 Indice

- [Panoramica](#-panoramica)
- [Funzionalità](#-funzionalità)
- [Modello del dominio](#-modello-del-dominio)
- [Struttura del progetto](#️-struttura-del-progetto)
- [Tecnologie](#️-tecnologie)
- [Come eseguire](#-come-eseguire)
- [Autori](#-autori)

---

## 🔎 Panoramica

**CantiereJava** è un'applicazione Java con interfaccia grafica **Swing** che simula la gestione di un'impresa edile.
L'impresa (`impresa.Impresa`) è composta da tre reparti:

| Reparto | Classe | Responsabilità |
|---|---|---|
| **Amministrativo** | `amministrativo.RepartoAmministrativo` | Gestione del personale (*interazione interna*) e di clienti, fornitori ed enti (*interazione esterna*) |
| **Operativo** | `operativo.RepartoOperativo` | Creazione, apertura e chiusura dei cantieri e gestione delle squadre di lavoro |
| **Statistiche** | `repartostatistiche.RepartoStatistiche` | Selezione, filtro e ordinamento degli elementi per generare report |

L'intero stato dell'impresa è serializzabile e può essere salvato su file o ricaricato in seguito.

---

## ✨ Funzionalità

### 👥 Amministrativo interno: personale
- Assunzione di quattro tipologie di dipendenti: **Dirigente**, **Impiegato**, **Operaio**, **Quadro**, ognuna con i propri campi (stipendio, anni di esperienza, ruolo, costo orario, ore di straordinario...).
- **Pagamento** del dipendente tramite codice fiscale, con logica polimorfica (`paga()`):
  - *Impiegato*: stipendio fisso;
  - *Dirigente*: stipendio più un bonus legato agli anni di esperienza;
  - *Quadro*: stipendio più ore di straordinario × costo orario;
  - *Operaio*: ore lavorate × costo orario.
- **Licenziamento** tramite codice fiscale, bloccato (`LicenziamentoException`) se il dipendente è impegnato in un lavoro.

### 🤝 Amministrativo esterno: rapporti con terzi
- Aggiunta e rimozione di **Clienti** (per codice fiscale), **Fornitori** ed **Enti** (per partita IVA).

### 🚧 Operativo: cantieri e squadre
- Creazione di un cantiere con nome, valore economico e responsabile:
  - valore **> 500.000 €** → il responsabile deve essere un **Dirigente**;
  - valore **< 500.000 €** → il responsabile può essere un **Dirigente** o un **Quadro**;
  - altrimenti viene sollevata `ResponsabileException`.
- Apertura e chiusura dei cantieri.
- Creazione e rimozione di **squadre** con codice univoco e caposquadra (che deve essere un **Quadro**).
- Aggiunta e rimozione di operai dalle squadre; un operaio già impegnato non può essere assegnato (`OperaioImpegnatoException`).
- Visualizzazione dei dipendenti liberi e della composizione delle squadre.

### 📊 Statistiche e report
- Scelta della lista da analizzare: dipendenti, cantieri, fornitori, clienti o enti.
- Ordinamento **crescente** o **decrescente**.
- Filtri: cantieri con valore ≥ 500.000, dipendenti non occupati, elementi compresi in un intervallo *min–max*.
- Implementazione generica basata su `Comparator<T>` e sull'interfaccia `Selectable<T>`.

### 💾 Persistenza
Dal menu **File** della finestra principale:
- **salva impresa su file**: serializza l'impresa in `impresa.dat`;
- **carica impresa da file**: deserializza un file scelto con `JFileChooser`;
- **crea nuova impresa**: riparte da un'impresa vuota.

Nel repository è incluso un file [`impresa.dat`](impresa.dat) di esempio.

---

## 🧩 Modello del dominio

```
Impresa
├── RepartoAmministrativo
│   ├── InterazioneInterna ──► Dipendente (abstract)
│   │                           ├── Dirigente
│   │                           ├── Impiegato
│   │                           ├── Operaio
│   │                           └── Quadro
│   └── InterazioneEsterno ──► Cliente · Fornitore · Ente
├── RepartoOperativo ──► Cantiere ──► Squadra (caposquadra: Quadro, operai: Operaio)
└── RepartoStatistiche ──► Ordinatore<T> · Selectable<T>
```

---

## 🗂️ Struttura del progetto

```
CantiereJava/
├── ambienteesterno/     # Cliente, Fornitore, Ente (soggetti esterni all'impresa)
├── amministrativo/      # Reparto amministrativo, interazione interna/esterna, tester
├── cantiere/            # Cantiere e Squadra
├── eccezioni/           # Eccezioni di dominio (Responsabile, Licenziamento, OperaioImpegnato, InvalidQuadro)
├── grafica/             # Finestre Swing e classe di avvio ImpresaStarter
├── impresa/             # Classe Impresa (radice del modello, Serializable)
├── operativo/           # Reparto operativo e tester
├── personale/           # Gerarchia dei dipendenti
├── repartostatistiche/  # Ordinamento e selezione generici per i report
└── impresa.dat          # Esempio di impresa serializzata
```

Finestre dell'interfaccia (`grafica/`):

| Classe | Finestra |
|---|---|
| `FrameImpresa` | Menu principale "Gestore impresa edile" |
| `AmministrativoInternoFrame` / `FrameDipendenti` | Gestione e assunzione dei dipendenti |
| `AmministrativoEsternoFrame` | Gestione di clienti, fornitori ed enti |
| `FrameOperativo` / `FrameCantiere` | Gestione dei cantieri e delle squadre |
| `FrameStatistiche` | Visualizzazione di statistiche e report |

---

## 🛠️ Tecnologie

- **Java** (Java SE, nessuna libreria esterna)
- **Swing / AWT** per l'interfaccia grafica
- **Java Serialization** (`ObjectOutputStream` / `ObjectInputStream`) per la persistenza
- Generics, `Comparator`, eccezioni personalizzate

---

## 🚀 Come eseguire

### Da IDE (Eclipse, IntelliJ, NetBeans)
1. Importa la cartella come progetto Java usando la root del repository come cartella dei sorgenti.
2. Esegui la classe **`grafica.ImpresaStarter`**.

### Da riga di comando
Dalla root del repository:

```bash
# compilazione
javac -encoding UTF-8 -d bin */*.java

# avvio
java -cp bin grafica.ImpresaStarter
```

> ℹ️ Il comando *salva impresa su file* scrive `impresa.dat` nella directory di lavoro corrente.

Le classi `amministrativo.AmministrativoTester` e `operativo.OperativoTester` contengono un `main` per provare la logica da console, senza interfaccia grafica.

---

## 👨‍💻 Autori

- **Antonio Zizzari**
- **Davide Iovino**
- **GiovanniBattista Sorrentino**
