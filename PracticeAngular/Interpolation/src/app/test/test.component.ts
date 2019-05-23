import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-test',
  // templateUrl: './test.component.html',
  template: `
  <h2>
  Welcome {{name}}
  </h2>
  <h2>{{greetUser(name)}}</h2>
  `,
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {
  public name = 'Dat';

  constructor() { }

  ngOnInit() {
  }
  greetUser(str: string): string {
    return `from a function: ${str}`;
  }
}
