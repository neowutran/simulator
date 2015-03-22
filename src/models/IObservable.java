/**
 * @author Martini Didier
 *
 */
package models;
import models.Materiaux;
public interface IObservable
{
        void addObserver(IObserver o);

        void deleteObserver(IObserver o);

        void notifyObservers(Materiaux mur[], int time);
}
