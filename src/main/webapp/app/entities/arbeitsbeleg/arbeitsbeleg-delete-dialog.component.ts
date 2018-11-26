import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IArbeitsbeleg } from 'app/shared/model/arbeitsbeleg.model';
import { ArbeitsbelegService } from './arbeitsbeleg.service';

@Component({
    selector: 'jhi-arbeitsbeleg-delete-dialog',
    templateUrl: './arbeitsbeleg-delete-dialog.component.html'
})
export class ArbeitsbelegDeleteDialogComponent {
    arbeitsbeleg: IArbeitsbeleg;

    constructor(
        private arbeitsbelegService: ArbeitsbelegService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.arbeitsbelegService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'arbeitsbelegListModification',
                content: 'Deleted an arbeitsbeleg'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-arbeitsbeleg-delete-popup',
    template: ''
})
export class ArbeitsbelegDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ arbeitsbeleg }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ArbeitsbelegDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.arbeitsbeleg = arbeitsbeleg;
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
