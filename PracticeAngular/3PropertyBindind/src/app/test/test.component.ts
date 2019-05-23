import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-test',
  // templateUrl: './test.component.html',
  template: `
  <h2>
  Welcome {{name}}
  </h2>
  <h2>{{greetUser(name)}}</h2>
  <input [id]="myId" type="text" value="Mountain">
  <input [disabled]="isDisabled" id="{{myId}}" type="text" value="Mountain">
  `,
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {
  public name = 'Dat';
  public myId = 'testId';
  public isDisabled = true;

  constructor() { }

  ngOnInit() {
  }
  greetUser(str: string): string {
    return `from a function: ${str}`;
  }
}
