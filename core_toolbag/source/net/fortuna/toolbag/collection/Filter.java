package net.fortuna.toolbag.collection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Ben Fortuna
 *  
 */
public class Filter {
    
    public static final int MATCH_ANY = 1;
    
    public static final int MATCH_ALL = 2;
    
    private static Log log = LogFactory.getLog(Filter.class);

    private Rule[] rules;
    
    private int type;

    /**
     * Constructor.
     * @param rule a rule that defines this filter
     */
    public Filter(final Rule rule) {
        this(new Rule[] {rule}, MATCH_ANY);
    }

    /**
     * Constructor.
     * @param rules an array of rules that define this filter
     */
    public Filter(final Rule[] rules, final int type) {
        this.rules = rules;
        this.type = type;
    }

    /**
     * Filter the given collection into a new collection.
     * @param c a collection to filter
     * @return a filtered collection
     */
    public final Collection filter(final Collection c) {        
        if (getRules() != null && getRules().length > 0) {
            // attempt to use the same type of concrete collection type
            // as is passed in..
            Collection filtered;
            try {
                filtered = (Collection) c.getClass().newInstance();
            }
            catch (Exception e) {
                filtered = new ArrayList();
            }

            if (type == MATCH_ALL) {
                List list = new ArrayList(c);
                List temp = new ArrayList();
                for (int n = 0; n < getRules().length; n++) {
                    for (Iterator i = list.iterator(); i.hasNext();) {
                        Object o = i.next();
                        if (getRules()[n].match(o)) {
                            temp.add(o);
                        }
                    }
                    list = temp;
                    temp = new ArrayList();
                }
                filtered.addAll(list);
            }
            else {
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
        try {
            return filtered.toArray((Object[]) Array.newInstance(
                    objects.getClass(), filtered.size()));
        }
        catch (ArrayStoreException ase) {
            log.warn("Error converting to array - using default approach", ase);
        }        
        return filtered.toArray();
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