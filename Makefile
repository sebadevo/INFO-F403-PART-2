all:
	jflex src/LexicalAnalyzer.flex
	javac -d bin -cp src/ src/Main.java
	jar cfe dist/part2.jar Main -C bin .

testing:
	java -jar dist/part2.jar -wt tree.tex test/euclid.co
	pdflatex tree.tex > /dev/null
	java -jar dist/part2.jar -wt multiplebracket.tex test/multiplebracket.co
	pdflatex multiplebracket.tex > /dev/null
	java -jar dist/part2.jar -wt primeNumbers.tex test/primeNumbers.co
	pdflatex primeNumbers.tex > /dev/null
	java -jar dist/part2.jar -wt test_1.tex test/test_1.co
	pdflatex test_1.tex > /dev/null


