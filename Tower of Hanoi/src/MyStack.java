import java.util.*;

public class MyStack<E> implements StackInterface<E>
{
    ArrayList<E> data = new ArrayList<E>();
    public void push(E o)
    {
        data.add(0,o);
    }
    public E peek()
    {
        if(data.size()==0)
            return null;
        else
            return data.get(0);
    }
    public E pop()
    {
        if(data.size()==0)
            return null;
        else
        {
            E x = data.get(0);
            data.remove(0);
            return x;
        }
    }
    public E get(int x)
    {
        return data.get(x);
    }
    public int size()
    {
        return data.size();
    }
    public boolean isEmpty()
    {
        if(data.size()==0)
            return true;
        else
            return false;
    }
    public void clear()
    {
        data.clear();
    }
}