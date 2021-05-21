import { isEmpty, head, get } from 'lodash/fp';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../core/auth';

interface NavItem {
  label: string;
  link: string;
}

@Component({
  selector: 'app-shell',
  templateUrl: './shell.component.html',
  styleUrls: ['./shell.component.scss']
})
export class ShellComponent implements OnInit {
  isAdmin$ = this.authService.isAdmin$;
  navList: NavItem[] = [];
  adminNav: NavItem[] = [{
    label: 'Admin',
    link: 'admin'
  }];
  defaultNav: NavItem[] = [
    {
      label: 'Classrooms',
      link: 'classrooms'
    },
    {
      label: 'Groups',
      link: 'groups'
    },
    {
      label: 'Subjects',
      link: 'subjects'
    },
    {
      label: 'Teachers',
      link: 'teachers'
    },
    {
      label: 'Schedule Generation',
      link: 'schedule-generation'
    }
  ];

  constructor(
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.setNavList();
    !isEmpty(this.navList) && this.router.navigate([ get('link', head(this.navList)) ], { relativeTo: this.route });
  }

  private setNavList() {
    this.navList = this.isAdmin$.value ? [ ...this.adminNav, ...this.defaultNav ] : this.defaultNav;
  }
}
