package de.core.quickplan.service.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * used for doing things on an Iterable
 * @author Sebastian Kohler
 *
 */
public class IterableService {
	/**
	 * converts the one type of Iterable into an ArrayList
	 * @param <S>
	 * @param <T>
	 * @param list
	 * @param func -> the function to convert the object
	 * @return
	 */
	public static <S,T> List<T> convert(Iterable<S> list, Function<S,T> func)
	{
		if(list == null) return null;
		List<T> result = new ArrayList<T>();
		for(S s : list)
		{
			result.add(func.apply(s));
		}
		return result;
	}
	public static <S,T> List<T> convert(List<S> list, Function<S,T> func)
	{
		if(list == null) return null;
		return list.stream().map(func).collect(Collectors.toList());
	}
}
