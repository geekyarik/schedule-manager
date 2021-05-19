import { reject } from 'lodash/fp';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ClassroomsService } from './classrooms.service';

@Component({
  selector: 'app-classrooms',
  templateUrl: './classrooms.component.html',
  styleUrls: ['./classrooms.component.scss']
})
export class ClassroomsComponent implements OnInit {
  form = this.fb.group({
    name: ['', Validators.required]
  });
  rooms: any = [];

  constructor(private service: ClassroomsService, private fb: FormBuilder) { }

  ngOnInit() {
    this.getRooms();
  }

  createRoom() {
    this.service.createRoom(this.form.value).subscribe(room => {
      this.form.reset();
      this.rooms = [...this.rooms, room]
    });
  }

  deleteRoom(id: string) {
    this.service.deleteRoom(id).subscribe(() => this.rooms = reject({ id }, this.rooms));
  }

  private getRooms() {
    this.service.getRooms().subscribe((rooms: any) => this.rooms = rooms);
  }

}
