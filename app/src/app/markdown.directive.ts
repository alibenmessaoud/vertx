import { Directive, ElementRef, OnInit, Renderer2 } from '@angular/core';
import * as marked from 'marked';

/**
 * Use the `marked` JS library (https://github.com/markedjs/marked) to convert the contents
 * of the decorated element from Markdown into HTML.
 * Install marked into your Angular project to make it available - `npm install --save marked`
 * create an element in your component.html and write some Markdown in it! E.g:
 * ```
 * <div appMarked>
 * # I am a header!
 *
 * I am a paragraph!
 * </div>
 * ```
 */
@Directive({
    selector: '[appMarkdown]'
})
export class MarkdownDirective implements OnInit {

    constructor(private elementRef: ElementRef,
                private renderer: Renderer2) { }

    ngOnInit() {
        const markText = this.elementRef.nativeElement.innerHTML;
        console.log(markText)
        if (markText && markText.length > 0) {
            const markdownHtml = marked(markText);
            console.log(markdownHtml)
            this.renderer.setProperty(this.elementRef.nativeElement, 'innerHTML', markdownHtml);
        }
    }
}
