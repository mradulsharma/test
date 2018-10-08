package com.madiv.learning;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.madiv.learning.Steps.Step;

public class StreamAndFilters {

	public static void main(String[] args) {
		StreamAndFilters filters = new StreamAndFilters();
		filters.doIt();
	}

	private void doIt() {

		
		
		// Filter on list, collect as List
		List<String> names = Arrays.asList("Maddy", "Mradul", "Mradulanand", "Ayaan");
		
		List<String> resultList = names.stream()
		.filter(name -> !name.contains("radu"))
		.collect(Collectors.toList());
		System.out.println(resultList);
		

		
		// Filter on set, collect as set
		Set<Step> steps = (new Steps()).getSteps();
		
		Set<Step> resultSet = steps.stream().
		filter(step -> !"A A".equals(step.getName()))
		.collect(Collectors.toSet());
		
		resultSet.forEach(System.out::println);
		
	}
	
}



class Steps{
	
	private Set<Step> steps = new LinkedHashSet<Step>();
	
	public Set<Step> getSteps() {
		// TODO Auto-generated method stub
		
		steps.add(new Step("aa", "A A"));
		steps.add(new Step("bb", "B B"));
		steps.add(new Step("cc", "C C"));
		steps.add(new Step("dd", "D D"));
		
		return steps;

	}
	
	
	class Step{
		
		public Step(String id, String name) {
			this.id = id;
			this.name = name;
		}
		@Override
		public String toString() {
			return "Step [id=" + id + ", name=" + name + "]";
		}
		private String id = null; 
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		private String name = null; 
	}
	
}
