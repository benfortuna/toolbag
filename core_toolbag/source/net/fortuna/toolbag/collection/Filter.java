package net.fortuna.toolbag.collection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Ben Fortuna
 *  
 */
public class Filter {

    private Rule[] rules;

    /**
     * Constructor.
     * @param rule a rule that defines this filter
     */
    public Filter(final Rule rule) {
        this(new Rule[] {rule});
    }

    /**
     * Constructor.
     * @param rules an array of rules that define this filter
     */
    public Filter(final Rule[] rules) {
        this.rules = rules;
    }

    /**
     * Filter the given collection into a new collection.
     * @param c a collection to filter
     * @return a filtered collection
     */
    public final Collection filter(final Collection c) {
        
        if (getRules() != null) {
            // attempt to use the same type of concrete collection type
            // as is passed in..
            Collection filtered;

            try {
                filtered = (Collection) c.getClass().newInstance();
            }
            catch (Exception e) {
                filtered = new ArrayList();
            }

            Iterator i = c.iterator();

            while (i.hasNext()) {
                Object o = i.next();

                for (int n = 0; n < getRules().length; n++) {
                    if (getRules()[n].match(o)) {
                        filtered.add(o);
                        break;
                    }
                }
            }

            return filtered;
        }
        
        return c;
    }

    /**
     * Returns a filtered subset of the specified array.
     * @param objects an array to filter
     * @return a filtered array
     */
    public final Object[] filter(final Object[] objects) {

        Collection filtered = filter(Arrays.asList(objects));

        return filtered.toArray((Object[]) Array.newInstance(
                objects.getClass(), filtered.size()));
    }
    
    /**
     * @return Returns the rules.
     */
    public Rule[] getRules() {
        return rules;
    }
    
    /**
     * @param rules The rules to set.
     */
    public void setRules(Rule[] rules) {
        this.rules = rules;
    }
}