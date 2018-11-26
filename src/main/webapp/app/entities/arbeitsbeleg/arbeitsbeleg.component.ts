import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IArbeitsbeleg } from 'app/shared/model/arbeitsbeleg.model';
import { Principal } from 'app/core';
import { ArbeitsbelegService } from './arbeitsbeleg.service';

@Component({
    selector: 'jhi-arbeitsbeleg',
    templateUrl: './arbeitsbeleg.component.html'
})
export class ArbeitsbelegComponent implements OnInit, OnDestroy {
    arbeitsbelegs: IArbeitsbeleg[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private arbeitsbelegService: ArbeitsbelegService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.arbeitsbelegService.query().subscribe(
            (res: HttpResponse<IArbeitsbeleg[]>) => {
                this.arbeitsbelegs = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInArbeitsbelegs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IArbeitsbeleg) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInArbeitsbelegs() {
        this.eventSubscriber = this.eventManager.subscribe('arbeitsbelegListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
