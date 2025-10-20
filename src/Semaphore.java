public abstract class Semaphore {

    protected int valeur=0; //compteur de permissions

    //Initialise le sémaphore à valeurInitiale si positive, sinon 0.
    protected Semaphore(int valeurInitiale){
	valeur = valeurInitiale>0 ? valeurInitiale:0;
    }

    // Tant qu'il n'y a pas de permissions le thread attend
    public synchronized void syncWait(){
	try {
	    while(valeur<=0){ // si je met 40 la valeur ici sera à 1 puis donc a 0 car dans semaphoreBInaire il le met a 1
		wait(); //met thread en attente
        }
	    valeur--; //quand valeur > 0 on consomme une permission
	} catch(InterruptedException e){}
    }
    //ensuite appele de syncSignal dans semaphore binaire donc on
    //arrive en bas


    public synchronized void syncSignal(){
	if(++valeur > 0) notifyAll();
    }
    //rajoute 1 a valeur donc il notify tout le monde et que c'est libre

    // Il faut mainteant faire le main
}
