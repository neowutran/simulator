/**
 * @author Martini Didier
 *
 */
package models;

import models.Materiaux;
public interface IObserver
{
        void update(Materiaux mur[], int time);
}
