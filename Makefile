all:
	jflex src/LexicalAnalyzer.flex
	javac -d bin -cp src/ src/Main.java
	jar cfe dist/part2.jar Main -C bin .
	java -jar dist/part2.jar test/euclid.co

testing:
	java -jar dist/part2.jar -wt tree.tex test/euclid.co
	pdflatex tree.tex > /dev/null
