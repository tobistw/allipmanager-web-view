import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITndSiemens } from 'app/shared/model/tnd-siemens.model';
import { TndSiemensService } from './tnd-siemens.service';

@Component({
    selector: 'jhi-tnd-siemens-delete-dialog',
    templateUrl: './tnd-siemens-delete-dialog.component.html'
})
export class TndSiemensDeleteDialogComponent {
    tndSiemens: ITndSiemens;

    constructor(private tndSiemensService: TndSiemensService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tndSiemensService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tndSiemensListModification',
                content: 'Deleted an tndSiemens'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tnd-siemens-delete-popup',
    template: ''
})
export class TndSiemensDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tndSiemens }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TndSiemensDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.tndSiemens = tndSiemens;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
