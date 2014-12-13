class canDoJive { public static void main(String[] b) {
	String a="can do jive"; int x=3,y=x+x/x,z=x-x;
	System.out.print(""+Character.toUpperCase(a.charAt(y+y))+a.charAt(x)+a.substring(z,y)+a.charAt(z)+a.charAt(y+y-x)); z=y-x;
	for(;y>z;y--) { System.out.print(""+a.charAt(y)+a.charAt((y+z)*(z+z))); }
	System.out.print(""+Character.toUpperCase(a.charAt(x+x+z))+a.charAt(z)+a.charAt(x+x+x)+a.charAt(z));
}}