import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IDashboard } from 'app/shared/model/dashboard.model';
import { DashboardService } from './dashboard.service';

@Component({
    selector: 'jhi-dashboard-update',
    templateUrl: './dashboard-update.component.html'
})
export class DashboardUpdateComponent implements OnInit {
    dashboard: IDashboard;
    isSaving: boolean;
    zeitstempel: string;

    constructor(private dashboardService: DashboardService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ dashboard }) => {
            this.dashboard = dashboard;
            this.zeitstempel = this.dashboard.zeitstempel != null ? this.dashboard.zeitstempel.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.dashboard.zeitstempel = this.zeitstempel != null ? moment(this.zeitstempel, DATE_TIME_FORMAT) : null;
        if (this.dashboard.id !== undefined) {
            this.subscribeToSaveResponse(this.dashboardService.update(this.dashboard));
        } else {
            this.subscribeToSaveResponse(this.dashboardService.create(this.dashboard));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDashboard>>) {
        result.subscribe((res: HttpResponse<IDashboard>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
