package com.jlh.lambda;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 * Created by Administrator
 * On 2016/8/6 0006.
 *
 * @description
 */

/**
 *
 * java8 lambda 编程样例
 */
public class Main {

    public static void main (String []args){
        ITrade i = (m)->m==null||m.trim().equals("")?false:true;
        System.out.println (i.check("a"));

        myList a = new myList();
        a.Insert("1","2","3","",null,"  ");
        a.Filter((m)->m==null||m.trim().equals("")?false:true);
        a.Print();

        ArrayList<Person> persons = new ArrayList<>();
        persons.add(new Person("jlh",15));
        persons.add(new Person("jlh2",16));
        persons.add(new Person("jlh3",15));
        persons.add(new Person("jlh",15));
        persons.sort(Comparator.comparing(Person::getName).reversed());//排序
        persons.forEach(System.out::println);//循环打印
        System.out.println(persons.stream().map(m->m.getOld()).reduce(10,(sum,m)->sum+m));//求所有和 基数从10开始
        System.out.println(persons.stream().mapToInt(m->m.getOld()).summaryStatistics());//统计平均值最大最小count和sum
        Map<Integer,List<String>> mp = persons.stream().collect(groupingBy(Person::getOld,mapping(Person::getName,toList())));//统计出年龄相同的人
        Map<String,Integer> mp2=persons.parallelStream().collect(groupingByConcurrent(p->p.getName(),summingInt(p->p.getOld())));//统计名字相同人的年龄和
        TreeMap<String,Integer> mp3=new TreeMap<>(mp2);
        List<String> names=new ArrayList<>();
        List<Integer> olds= new ArrayList<>();
        System.out.println (mp);
        System.out.println (mp2);
        System.out.println (mp3);
        //mp3.descendingKeySet().stream().forEach(m->{names.add(m);olds.add(mp3.get(m));}); //倒序遍历添加到两个数组中
        names=mp3.descendingKeySet().stream().collect(toList());
        olds=mp3.descendingKeySet().stream().map(m-> mp3.get(m)).collect(toList());//直接遍历赋值给数组
        System.out.println (names);
        System.out.println(olds);

    }

    static class Person{
        private String name;
        private Integer old;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getOld() {
            return old;
        }

        public void setOld(Integer old) {
            this.old = old;
        }

        public Person(String name, Integer old) {
            this.name = name;
            this.old = old;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", old=" + old +
                    '}';
        }
    }
}
class myList{
    List<String> m =new ArrayList<>();
    public void Insert (String ... param){
        for (String tmp: param)
            m.add(tmp);
    }
    public void Filter (ITrade t){
       m=m.stream().filter(s -> t.check(s)).collect(ArrayList::new,ArrayList::add,ArrayList::addAll);//toList 只能返回List 可以通过这种方式返回 Arraylist
    }
    public void Print(){
        m.forEach(System.out::println);
    }
}

