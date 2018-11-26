import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IArbeitsbeleg } from 'app/shared/model/arbeitsbeleg.model';
import { ArbeitsbelegService } from './arbeitsbeleg.service';
import { IUser, UserService } from 'app/core';
import { ITblTermine } from 'app/shared/model/tbl-termine.model';
import { TblTermineService } from 'app/entities/tbl-termine';

@Component({
    selector: 'jhi-arbeitsbeleg-update',
    templateUrl: './arbeitsbeleg-update.component.html'
})
export class ArbeitsbelegUpdateComponent implements OnInit {
    arbeitsbeleg: IArbeitsbeleg;
    isSaving: boolean;

    users: IUser[];

    tbltermines: ITblTermine[];
    datumLeistungDp: any;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private arbeitsbelegService: ArbeitsbelegService,
        private userService: UserService,
        private tblTermineService: TblTermineService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ arbeitsbeleg }) => {
            this.arbeitsbeleg = arbeitsbeleg;
        });
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.tblTermineService.query().subscribe(
            (res: HttpResponse<ITblTermine[]>) => {
                this.tbltermines = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.arbeitsbeleg.id !== undefined) {
            this.subscribeToSaveResponse(this.arbeitsbelegService.update(this.arbeitsbeleg));
        } else {
            this.subscribeToSaveResponse(this.arbeitsbelegService.create(this.arbeitsbeleg));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IArbeitsbeleg>>) {
        result.subscribe((res: HttpResponse<IArbeitsbeleg>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }

    trackTblTermineById(index: number, item: ITblTermine) {
        return item.id;
    }
}
