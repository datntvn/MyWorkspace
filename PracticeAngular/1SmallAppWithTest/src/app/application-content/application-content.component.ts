import { Component, OnInit, Input } from '@angular/core';
import { Title } from '@angular/platform-browser';

declare var $: any;

@Component({
  selector: 'app-application-content',
  templateUrl: './application-content.component.html',
  styleUrls: ['./application-content.component.css']
})
export class ApplicationContentComponent /* implements OnInit */ {

  // constructor() { }

  // ngOnInit() {
  // }
  @Input()
  title: string;

  showModal(): void {
    $('.ui.modal').modal('show');
  }

}
