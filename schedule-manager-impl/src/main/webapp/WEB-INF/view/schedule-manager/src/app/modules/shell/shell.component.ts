import { takeWhile, map, switchMap } from 'rxjs/operators';
import { isEmpty, head, get } from 'lodash/fp';
import { Component, OnDestroy, OnInit } from '@angular/core';
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
export class ShellComponent implements OnInit, OnDestroy {
  alive = true;
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
  publicNav = [{
    label: 'Schedule Board',
    link: 'board'
  }];

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
    this.navList = [...this.publicNav];

    this.authService.isAuthenticated$.pipe(
      takeWhile(() => this.alive),
      switchMap((isAuthenticated: boolean) => this.isAdmin$.pipe(map((isAdmin: boolean) => [isAuthenticated, isAdmin])))
    )
    .subscribe(([isAuthenticated, isAdmin]) => {
      const newNav = isAdmin ? [ ...this.defaultNav, ...this.adminNav ] : this.defaultNav;
      this.navList = isAuthenticated ? [...this.publicNav, ...newNav] : [...this.publicNav];
    });
  }

  ngOnDestroy() {
    this.alive = false;
  }
}
