import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScheduleGenerationComponent } from './schedule-generation.component';

describe('ScheduleGenerationComponent', () => {
  let component: ScheduleGenerationComponent;
  let fixture: ComponentFixture<ScheduleGenerationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScheduleGenerationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ScheduleGenerationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
