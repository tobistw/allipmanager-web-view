import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITblFunkmessung } from 'app/shared/model/tbl-funkmessung.model';
import { TblFunkmessungService } from './tbl-funkmessung.service';

@Component({
    selector: 'jhi-tbl-funkmessung-delete-dialog',
    templateUrl: './tbl-funkmessung-delete-dialog.component.html'
})
export class TblFunkmessungDeleteDialogComponent {
    tblFunkmessung: ITblFunkmessung;

    constructor(
        private tblFunkmessungService: TblFunkmessungService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tblFunkmessungService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'tblFunkmessungListModification',
                content: 'Deleted an tblFunkmessung'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tbl-funkmessung-delete-popup',
    template: ''
})
export class TblFunkmessungDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tblFunkmessung }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(TblFunkmessungDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.tblFunkmessung = tblFunkmessung;
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
