package Coursework;

class Minimal_latex {
// compile with javac, run with
//     java Minimal_latex > a.tex
// compile from .tex to .pdf with
//     pdflatex a.pdf
// view the resulting file a.pdf in Acrobat or Chrome

    public static void main (String[] args) {
        System.out.println("\\documentclass{article}");
        System.out.println("\\begin{document}");
        System.out.println("Hello, this is my text");
        System.out.println("\\end{document}");
    }
    
}
