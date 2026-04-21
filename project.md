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


## Interface
* **Layout**:
  * Send
  * Receive
  
## Class
* **Applicativo : `Layout`**
* **Trasporto : `Layout`**
* **Rete : `Layout`**
* **DataLink : `Layout`**
* **Fisico : `Layout`**