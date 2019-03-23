import { Directive, Input, Renderer, ElementRef } from '@angular/core';

@Directive({
   selector: '[appHighLightDirect]'
})

export class HighlightDirective {
    constructor( el: ElementRef, renderer: Renderer) {
        renderer.setElementStyle(el.nativeElement, 'backgroundColor', 'blue');
    }
}
