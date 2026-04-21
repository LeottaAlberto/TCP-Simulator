# TCP-Simulator
## Spiegazione
Simulare il comportamento del TCP andando a riprodurre i vari layer.
Bisogna dare la possibilità di creazione di un messaggio (data) da incapsulare ad ogni passaggio di layer.
Una volta creato il frame bisogna "bloccarlo" in uno stack simulato per poi decapsularlo e far leggere il messaggio al livello applicativo simulato.

### Struttura
**Livello Applicativo** -> **Livello Trasporto** -> **Livello Rete** -> **Livello DataLink** -> **Livello Fisico**

`Applicativo`: contiene solo il messaggio (leggere o scrivere)
`Trasporto`: add/rm indirizzo fisico del mittente e destinatario
`Rete`: add/rm indirizzo logico del mittente e destinatario
`DataLink`:
`Fisico`:

Ogni livello ha a disposizione un metodo: **Send** e **Receive**
* `Send`: incapsula il messaggio aggiungendo le informazioni di quel livello e spedisce tutto al livello inferiore
* `Receive`: decapsula il messaggio e lo invia al livello superiore

# Sequenza
Creare una lista di host, contententi queste informazioni: *IP*, *MAC*, *NAME*, *STATUS*, *LINK-TYPE*, *HOST-LIST*.
Ogni Host ha la capacita' di inviare/visualizzare messaggi o accendersi.
All'accensione ogni host manda un messaggio in broadcast chiendo a tutti gli host presenti nella rete di dire il loro *NAME*.

* Sample: IP: x.x.x.x - Come Ti Chiami?

Quando un host vuole inviare un messaggio deve, prima, selezionare il destinatario dalla *HOST-LIST*.
Qunado un host riceve un invito a presentarsi dovra' condividere il suo *NAME*

## Interface
* **Layout**:
  * Send
  * Receive
* **NetDevice**
  * onReceiveData
  
## Layout Class
* **Applicativo : `Layout`**
  * Da dove nasce il messaggio che si vuole inviare
  * Protocollo di invio: HTTP, HTTPS, SMTP, FTP
* **Trasporto : `Layout`**
  * Att: *PORTA*
  * Crea il segmento
    * Inserisce il tipo di protocollo: TCP o UDP
    * Porta dest/mit
    * Lunghezz messaggio
    * Spezzetta il messaggio in n parti. Solo la prima prenseta porta e lunghezza
    * Segmento:
      * 0: UDP/TCP - porta mitt - porta dest - lenght - contatore - messaggio spezzato
      * 0 + n: UDP/TCP - porta mitt - porta dest - lenght - contatore - messaggio spezzato
* **Rete : `Layout`**
  * Crea il Datagramma
    * Inserisce l'indirizzo IP mittente e destinatario
* **DataLink : `Layout`**
  * Oltre al send e al receive ci sara' un terzo metodo per la ricerca del MAC dell'host di destinaizone
  * aggiunge MAC destinazione e mittente
* **Fisico : `Layout`**
  * Rallenta l'invio dei pacchetti in relazione al tipo di collegamento dell'host

## Host Class
* **Host**
  * Name
  * NetAdapter
  * Status
* **NetAdapter**
  * ID
  * MAC
  * NetDevice
  * Application
  * Transport
  * Network
  * DataLink
  * Phisical

`
public interface DispositivoDiRete {
  void onDatiRicevuti(String mittente, String dati);
}
`
` public class Host implements DispositivoDiRete {
    private String nomeHost;
    private SchedaDiRete scheda;

    public Host(String nomeHost, String ip, String mac) {
        this.nomeHost = nomeHost;
        // Passo l'Host come interfaccia, non come classe concreta
        this.scheda = new SchedaDiRete(this, ip, mac);
    }

    @Override
    public void onDatiRicevuti(String mittente, String dati) {
        System.out.println(nomeHost + " sta leggendo il messaggio: " + dati);
    }
} `
` public class SchedaDiRete {

    private DispositivoDiRete riff;
    private String ip;
    private String mac;

    public SchedaDiRete(DispositivoDiRete os, String ip, String mac) {
        this.riff = os;
        this.ip = ip;
        this.mac = mac;
    }

    public void decapsulaDati(String frameEthernet) {
        // ... logica per togliere MAC, IP, TCP ...
        String payload = "Ciao"; 
        
        // Passo i dati al livello superiore, chiunque esso sia
        riff.onDatiRicevuti("192.168.1.10", payload);
    }
} `